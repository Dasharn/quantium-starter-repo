@echo off

rem Activate the project virtual environment
venv\Scripts\activate.bat

rem Execute the test suite
python -m unittest test_file.py

rem Capture the exit code of the test suite
set exit_code=%errorlevel%

rem Deactivate the virtual environment
deactivate

rem Return the exit code
exit /b %exit_code%
