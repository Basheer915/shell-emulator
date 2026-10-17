bat
@echo off
cd /d "%~dp0"
gradle run --args="--vfs ./vfs --script ./startup.txt"
pause
