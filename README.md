# ⚓ Battleship Game — Java

A fully featured, terminal-based Battleship game written in Java. Play a classic 1v1 game of naval strategy against a computer opponent that hunts your ships intelligently. Built entirely from scratch using core Java with no external libraries.

---

## 📋 Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [How to Run](#how-to-run)
- [How to Play](#how-to-play)
- [Run Example](#run-example)
- [Ships Reference](#ships-reference)

---

## ✨ Features

- **10×10 grid** with row and column coordinates clearly displayed
- **5 ships** to place — each with a choice of direction (up, down, left, right)
- **Smart computer AI** that hunts adjacent cells after a successful hit
- **Full input validation** — re-prompts on invalid directions, out-of-range coordinates, and already-attacked cells
- **Quit or restart at any time** by typing `quit` or `restart` at any prompt
- **Play again prompt** after the game ends naturally
- **Dramatic ship-sunk banners** and clear hit/miss separators for every exchange
- **Exception handling** throughout — no crashes on bad input

---

## 🗂 Project Structure

```
battleshipGame/
├── Battleship.java          # Entry point — main game loop and restart/quit handling
├── Player.java              # Human player input, ship placement, attack logic
├── Computer.java            # Computer AI — random placement, predictive targeting
├── Boards.java              # 10×10 board logic, hit/miss tracking, board printing
├── Ship.java                # Ship model — type, size, direction, position
├── Coordinate.java          # (x, y) coordinate model
├── QuitGameException.java   # Thrown when player types "quit"
└── RestartGameException.java# Thrown when player types "restart"
```

---

## ✅ Requirements

- **Java 8 or higher** (uses only standard library)
- A terminal / command prompt

---

## ▶ How to Run

**1. Clone the repository**
```bash
git clone https://github.com/mompho/Battleship-Game.git
cd Battleship-Game
```

**2. Compile all Java files**
```bash
javac battleshipGame/*.java
```

**3. Run the game**
```bash
java battleshipGame.Battleship
```

---

## 🎮 How to Play

### Ship Placement
At the start of each game you place 5 ships on your 10×10 board. For each ship you will be asked for:
- A **direction** — `u` (up), `d` (down), `r` (right), or `l` (left)
- An **X-coordinate** (column, 0–9) — where the ship starts
- A **Y-coordinate** (row, 0–9) — where the ship starts

The ship extends from that starting cell in the chosen direction. If the placement is out of bounds or overlaps another ship you will be asked to try again.

### Combat
Once all ships are placed the game alternates between your turn and the computer's:

- **Your turn** — enter X and Y coordinates to fire at the computer's board
- **Computer's turn** — the computer fires automatically; after a hit it will target adjacent cells to sink your ship

### Board Symbols

| Symbol | Meaning |
|--------|---------|
| `~` | Empty water |
| `A` | Aircraft Carrier |
| `B` | Battleship |
| `S` | Submarine |
| `D` | Destroyer |
| `P` | Patrol Boat |
| `H` | Hit (on your attack board) |
| `M` | Miss (on your attack board) |
| `X` | Sunk / destroyed cell |

### Board A vs Board B
After each round two boards are shown:
- **Board A** — your own board, showing your ships and where the computer has hit (`X`)
- **Board B** — your attack board, showing where you have fired (`H` = hit, `M` = miss)

### Winning
The first player to sink all 5 of the opponent's ships wins.

### Quit & Restart
Type `quit` or `restart` at **any** input prompt — direction, coordinate, or attack — to exit or start a fresh game instantly.

---

## 🖥 Run Example

```
==========================================
  WELCOME TO BATTLESHIP!
  You are playing against the computer.
  Type 'quit' to exit or 'restart' to
  start over at ANY input prompt.
==========================================

==========================================
  TIME TO PLACE YOUR SHIPS!
==========================================
  (Type 'quit' to exit or 'restart' to start over at any time)

Your board:
                0       1       2       3       4       5       6       7       8       9
0               ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
1               ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
...

Placing your Aircraft Carrier (size 5)...
  (Type 'quit' to exit or 'restart' to start over at any time)
Enter direction ('u' up, 'd' down, 'r' right, 'l' left):
d
Enter X-coordinate (column 0-9):
2
Enter Y-coordinate (row 0-9):
2
  Aircraft Carrier (size 5) placed successfully!
        0       1       2       3       4       5       6       7       8       9
0       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
1       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
2       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
3       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
4       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
5       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
6       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
7       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
8       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
9       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~

[ ... remaining ships placed ... ]

==========================================
  ALL SHIPS PLACED! READY FOR BATTLE.
==========================================

The Computer has finished setting up its board as well. Time to play!
'X' represents where the computer has attacked your board.

##########################################
  ROUND 1
##########################################

>>> YOUR TURN <<<
  (Type 'quit' to exit or 'restart' to start over at any time)
Enter X-coordinate to attack (column 0-9):
3
Enter Y-coordinate to attack (row 0-9):
4
--------------------------------------------------
  YOUR ATTACK: MISS! Better luck next shot.
--------------------------------------------------

>>> COMPUTER'S TURN <<<
  Computer attacks (6, 2)...
--------------------------------------------------
  COMPUTER ATTACK: HIT!  The enemy struck your ship!
--------------------------------------------------

--- YOUR BOARD (Board A: your ships) ---
        0       1       2       3       4       5       6       7       8       9
0       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
1       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
2       ~       ~       A       ~       ~       ~       X       S       S       ~
3       ~       ~       A       ~       ~       ~       ~       ~       ~       ~
...

--- YOUR ATTACK BOARD (Board B: hits on enemy) ---
        0       1       2       3       4       5       6       7       8       9
0       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
1       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
2       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
3       ~       ~       ~       ~       ~       ~       ~       ~       ~       ~
4       ~       ~       ~       M       ~       ~       ~       ~       ~       ~
...

##########################################
  ROUND 2
##########################################

>>> YOUR TURN <<<
Enter X-coordinate to attack (column 0-9):
5
Enter Y-coordinate to attack (row 0-9):
7
--------------------------------------------------
  YOUR ATTACK: HIT!  Great shot, soldier!
--------------------------------------------------

[ ... game continues ... ]

==================================================
*** YOU SUNK THE ENEMY'S BATTLESHIP! ***
==================================================

[ ... more rounds ... ]

==========================================
  GAME OVER! CONGRATULATIONS, YOU WON!
==========================================

Would you like to play again? (yes / no):
no

==========================================
  Thanks for playing Battleship! Goodbye.
==========================================
```

---

## 🚢 Ships Reference

| Ship | Symbol | Size |
|------|--------|------|
| Aircraft Carrier | `A` | 5 cells |
| Battleship | `B` | 4 cells |
| Submarine | `S` | 3 cells |
| Destroyer | `D` | 3 cells |
| Patrol Boat | `P` | 2 cells |
