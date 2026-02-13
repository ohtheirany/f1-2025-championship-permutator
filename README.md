## Why I built this
I was following the 2025 Formula 1 Championship. Last year, going into the last race, three drivers could win the championship title, which is extremely rare. 
News outlets began publishing permutations of positions for each driver's winning cases, (e.g. for Max Verstappen to win, the positions would have to be Max P2, Lando P6, Piastri P3) 
At the same time, I was taking a discrete mathematics course which I was loving and wondered how permutations like these would be computer by programming.

## Case constraints
We are not permuting all possible race outcomes — 21! —, just permutations for each **Championship Contender's** winning cases. There were three contenders for the trophy.
There are only 11 possible points-awarding and no points-awarding positions; there are points for the top 10 finishers and every positions beyond 10th gets 0 points.
So, instead of the hypothetical — assuming all drivers on grid finish the race — of 21! permutations, the program would output < 11 x 10 x 9 results. 

First place in a race is awarded 25 points.
So, a rival contender is defined as having a gap of <= 25 to the points of the leader or is the leader;

Leading up to Abu Dhabi, the points were as follows:
  Lando - 408
  Max - 396
  Oscar - 392


## What the program does
- Loads the Abu Dhabi race lineup from `AbuDhabi.json` (driver names by finishing position).
- Loads the **pre-race points standings** from `pointsBeforeAbuDhabi.json`. This is to simulate having every race prior to Abu Dhabi totaled from individual race json files.
- Prompts the user to input the **first name** of a title contender.
- Generates all contender-only permutations (positions **P1–P10** plus **P11** representing “11th or worse” / 0 points).
- Prints each winning permutation as exact positions for the contenders.


## How permutations are generated (high-level)
Solution — backtracking algorithm
1. Fix the target driver to a finishing position.
2. Assign finishing positions to each rival contender from the available set:
   - Positions considered: **1–11**
   - **11 = 11th or worse**, worth **0 points**
3. If any rival’s simulated points exceed the target’s simulated points, that branch is discarded.
4. When all rivals have assigned positions and none beat the target, the scenario is saved as a `Permutation`.

Notes:
- This model only considers **title contenders**, not the full grid.
- Tie-break rules (countback) are not implemented; ties are treated as not guaranteed wins.

---

## Project structure
- `Driver` — stores a driver's name, points, and generated permutations
- `SimDriver` — a simulated driver + position + simulated points
- `Permutation` — a snapshot of one valid contender-only finishing scenario
- `Race` (POJO) — used for race results and points snapshots JSON
- `RaceProcessor` — initializes lineup, applies points snapshot, awards race points
- `Permutor` — generates championship-winning permutations for a target driver
- `Main` — CLI entrypoint: loads JSON, asks user for contender, prints permutations

---

## `AbuDhabi.json`
Race lineup by finishing position.

## Known issue / planned refactor
In the simplified model (3 contenders, positions P1–P10 plus P11 = “11th or worse”, no duplicate positions), the total number of possible contender-only assignments is **11 × 10 × 9 = 990**.  
But the sum of “championship-winning” permutations produced across all contenders is 1012 permutations.

I think this is because **tie-break rules are not implemented**. Right now, a points tie is treated as “not losing,” which can cause the same finishing-position assignment to be counted as a win for more than one contender depending on which driver is selected as the target.

**Planned fix:** Refactor the validation logic to explicitly handle ties (either by implementing countback tie-breakers or by treating ties as not guaranteed wins) and add small test cases to verify totals and correctness.

