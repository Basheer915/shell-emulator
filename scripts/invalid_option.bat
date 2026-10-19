
@echo off
cd /d "%~dp0\.."

echo Testing an invalid command-line option...
call gradlew.bat run --args="--wrong ./vfs --script ./startup.txt"
