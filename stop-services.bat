@echo off
echo Stopping all Spring Boot servers...

:: Specify the ports of the services here
set PORTS=8761 8080 8081 8082 8083

for %%P in (%PORTS%) do (
    echo Stopping server on port %%P...
    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%%P') do (
        echo Killing process with PID %%a...
        taskkill /PID %%a /F >nul 2>&1
    )
)

echo All specified servers have been stopped.
pause
