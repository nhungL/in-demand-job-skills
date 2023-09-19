# run-python.sh
# Change directory to the Python folder
# shellcheck disable=SC2164
cd python

# Activate a Python virtual environment
python3 -m venv venv
source venv/bin/activate

# Install Python dependencies
pip3 install -r requirements.txt

# Deactivate the virtual environment
deactivate
