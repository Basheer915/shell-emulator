
@echo off
cd /d "%~dp0\.."

echo Testing missing command-line arguments...
call gradlew.bat run

