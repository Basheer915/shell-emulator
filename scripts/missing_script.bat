
@echo off
cd /d "%~dp0\.."

echo Testing a nonexistent startup script...
call gradlew.bat run --args="--vfs ./vfs --script ./missing.txt"


