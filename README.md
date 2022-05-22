# Monopoly

## Table of Contents
- [Code](#code)
    - [Main](#main)
    - [Objects](#objects)
        - [Action](#action)
        - [Frame](#frame)
        - [Game](#game)
        - [Player](#player)
        - [Space](#space)
    - [Utils](#utils)
- [Resources](#resources)
    - [Board](#board)
    - [Tokens](#tokens)
   
   
## Code

### Main
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Main.java` | Starts the game and launches it |

### Objects

#### Action
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Action.java` | Class | Creates a new action |
| `ActionType.java` | Enum | Contains the different action types an action can consist of |

#### Frame
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Frame.java` | Class | Creates and manages the GUI assets of the application |

#### Game
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Game.java` | Class | Creates and manages the game |
| `GameStatus.java` | Enum | Contains the different statuses a game can have |

#### Player
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Player.java` | Class | Creates and manages players |
| `PlayerStatus.java` | Enum | Contains the different statuses a player can have |
| `Token.java` | Enum | Contains the different tokens a player can have |

#### Space
| Name | Type | Description |
| ---- | ---- | ----------- |
| `Space.java` | Class |  |
| `SpaceType.java` | Enum | Contains the different types a space can be |
| `Card.java`, `Chance.java`, `CommunityChest.java` | Class | Spaces of the `Card` types |
| `FreeParking.java` | Class | Space of the `Free Parking` type |
| `Go.java` | Class | Space of the `Go` type |
| `GoToJail.java` | Class | Space of the `Go to Jail` type |
| `Property.java`, `Set.java`, `Station.java`, `Street.java`, `Utility.java` | Class | Spaces of the `Property` types |
| `Tax.java`, `IncomeTax.java`, `LuxuryTax.java` | Class | Spaces of the `Tax` types |

### Utils

| Name | Type | Description |
| ---- | ---- | ----------- |
| `CalculateAspectRatio.java` | Class | Resizes assets for the `Frame` |
| `ConsoleColors.java` | Class | Allows the usage of colors in the IntelliJ console |
