# Optomizer 3.1.0

Optomizer is a small cross-platform Java Swing app that helps you change process priority using a simple GUI. It is designed for Linux, macOS, and Windows, and it requires administrator/root privileges to apply priority changes.

This version focuses on a lightweight UI with PID selection and quick actions to raise or reset process priority.

## Key Features
- GUI for selecting a process ID (PID) from a live list
- One-click actions to set a process to high priority or restore normal priority
- Built-in Help, Settings, and Credits dialogs
- Separate OS-specific implementations for Linux, macOS, and Windows

## Requirements
- Java 25 or newer
- Linux, macOS, or Windows (Windows 8+ recommended)
- Administrator/root privileges (required to change priority)

## How It Works
- Linux: uses `ps` to list processes and `renice` to set priority
- macOS: uses `ps` to list processes and `renice` to set priority
- Windows: uses `tasklist` to list processes and `wmic` to set priority

## Run From Source
1. Open a terminal in `Optomizer_V3.1.0`.
2. Compile:
```bash
javac -cp src -d bin src/App.java
```
3. Run:
```bash
java -cp bin:src App
```

On Windows, use:
```bat
javac -cp src -d bin src\App.java
java -cp bin;src App
```

Note: `Logo.png` must be available on the classpath. Keeping `src/Logo.png` and adding `src` to the classpath is the simplest approach.

## Usage Notes
- You will be asked to confirm that you are running with administrator privileges.
- Entering the wrong PID can affect system stability.
- Changing priority does not guarantee performance improvements; results depend on the OS and hardware.

## Support
- Help and documentation are available via the built-in Help button in the app.
- Community support is available via the project's Discord and GitHub wiki linked inside the app.

## Credits
- AlphaWolf6940: coding
- JoeDuck2020: original idea
