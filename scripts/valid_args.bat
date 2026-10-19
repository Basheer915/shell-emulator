
@echo off
cd /d "%~dp0\.."

echo Testing valid command-line arguments...
call gradlew.bat run --args="--vfs ./vfs --script ./startup.txt"

