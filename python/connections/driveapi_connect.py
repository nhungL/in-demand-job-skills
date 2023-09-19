from google.oauth2 import service_account
from google.oauth2.credentials import Credentials
from googleapiclient.discovery import build

def get_service(api_name, api_version, scopes, key_file_location):
    """Get a service that communicates to a Google API.

    Args:
        api_name: The name of the api to connect to.
        api_version: The api version to connect to.
        scopes: A list auth scopes to authorize for the application.
        key_file_location: The path to a valid service account JSON key file.

    Returns:
        A service that is connected to the specified API.
    """

    credentials = service_account.Credentials.from_service_account_file(
        key_file_location)

    scoped_credentials = credentials.with_scopes(scopes)

    # Build the service object.
    service = build(api_name, api_version, credentials=scoped_credentials)

    return service