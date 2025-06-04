# The Silver Dollar Game

A two-player text-based Java game where players take turns moving coins on a strip, following specific movement rules. First player to make the last legal move wins!

## 🎮 Game Overview

- Two players alternate turns
- Coins move left, cannot jump over others, and only one per square
- Game ends when no moves remain
- Winner = player who makes the last move

## 🧾 Features

- Randomized game board (default or user-defined coin count)
- Full rule enforcement (no illegal moves allowed)
- Clear board printing and user prompts
- Written in clean, modular Java using OOP

## 🛠️ File Descriptions

- `CoinStrip.java` — main class that represents the game board and enforces rules
- `PlayGame.java` — driver class that manages player interaction and prints board

## 🚀 How to Run

```bash
javac src/*.java
java src/PlayGame
