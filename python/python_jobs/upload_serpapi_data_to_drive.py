from googleapiclient.errors import HttpError
from googleapiclient.http import MediaFileUpload

import pandas as pd
import zipfile
from datetime import datetime
import time
import os

from connections.driveapi_connect import get_service

def load_file_to_drive(csv_filename, folder_id, service):
    current_date = datetime.now().strftime('%Y%m%d')
    zip_filename = f'data_{current_date}.zip'

    with zipfile.ZipFile(zip_filename, 'w', zipfile.ZIP_DEFLATED) as zipf:
        zipf.write(csv_filename, arcname=f'data_{current_date}.csv')

    print(f'DataFrame saved as {zip_filename}')

    # Define the path to the ZIP file
    zip_file_path = zip_filename

    # Create a file metadata
    file_metadata = {'name': zip_file_path, 'parents': [folder_id]}
    media = MediaFileUpload(zip_file_path,mimetype='application/zip')
    file = service.files().create(body=file_metadata, media_body=media,
                                  fields='id').execute()

    os.remove(zip_filename)
    print(F'File ID: {file.get("id")} uploaded successfully to Drive')


def get_data_files(service, folder_id):
    results = service.files().list(
        supportsAllDrives=True,
        includeItemsFromAllDrives=True,
        q=f"'{folder_id}' in parents and trashed = false",
        fields = "nextPageToken, files(id, name)").execute()

    items = results.get('files', [])

    if not items:
        print('No files found in folder.')
        return
    print('Files in folder:')
    for item in items:
        print(u'{0} ({1})'.format(item['name'], item['id']))


def upload_data(df):
    """Shows basic usage of the Drive v3 API.
    Prints the names and ids of the first 10 files the user has access to.
    """
    csv_filepath = 'python/data/csv_file.csv'
    df.to_csv(csv_filepath, index=False)

    # Define the auth scopes to request.
    scope = 'https://www.googleapis.com/auth/drive'
    key_file_location = os.getenv('GCP_KEY_FILE')

    try:
        # Authenticate and construct service.
        service = get_service(
            api_name='drive',
            api_version='v3',
            scopes=[scope],
            key_file_location=key_file_location)

        # Call the Drive v3 API
        folder_id = os.getenv('DRIVE_FOLDER_ID')
        print(f'Drive Folder ID: {folder_id}')

        load_file_to_drive(csv_filepath, folder_id, service)

        os.remove(csv_filepath)
        # time.sleep(5)
        # get_data_files(service, folder_id)

    except HttpError as error:
        print(f'An error occurred: {error}')