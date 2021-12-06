package ticTacToeWithoutPrimitives

import spock.lang.Specification
import spock.lang.Unroll
import ticTacToeWithoutPrimitives.entity.ActivePlayer
import ticTacToeWithoutPrimitives.entity.GameState
import ticTacToeWithoutPrimitives.entity.Koordinate

class TicTacToeTest extends Specification {

    def "Der initiale Zustand wird korrekt angezeigt."() {
        given:
        TicTacToe sut = new TicTacToe()

        when:
        def output = sut.getOutput()

        then:
        output == """Active Player: Bud
                    |ooo
                    |ooo
                    |ooo
                    |""".stripMargin()
    }

    @Unroll
    def "Die valide Eingabe (x=#x, y=#y) fuer Spieler #player wird korrekt eingetragen."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields.get(new Koordinate(x, y)) == player.getPiece()

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 | x | y
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 0 | 0
        ActivePlayer.PLAYER_TWO | 'x'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 1 | 1
        ActivePlayer.PLAYER_ONE | 'x'  | 'o'  | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | 0 | 2
        ActivePlayer.PLAYER_TWO | 'x'  | 'o'  | 'x'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | 0 | 1
        ActivePlayer.PLAYER_ONE | 'x'  | '+'  | 'x'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | 0 | 1
    }

    @Unroll
    def "Die nicht valide Eingabe (x=#x, y=#y) fuer Spieler #player wird nicht eingetragen."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]
        def initialValue = new HashMap(sut.board.fields)

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields == initialValue

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 | x  | y
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | -1 | 0
        ActivePlayer.PLAYER_TWO | '+'  | 'x'  | 'o'  | 'o'  | '+'  | 'x'  | '+'  | 'o'  | 'x'  | 0  | -1
        ActivePlayer.PLAYER_ONE | '+'  | 'x'  | 'o'  | '+'  | 'o'  | 'x'  | 'o'  | '+'  | 'x'  | -1 | -1
        ActivePlayer.PLAYER_TWO | 'x'  | 'o'  | 'x'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | 3  | 2
        ActivePlayer.PLAYER_ONE | '+'  | 'x'  | 'o'  | 'o'  | '+'  | 'x'  | '+'  | 'o'  | 'x'  | 2  | 3
    }

    @Unroll
    def "Das bereits besetzte Feld (x=#x, y=#y) fuer Spieler #player wird nicht eingetragen."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]
        def initialValue = new HashMap(sut.board.fields)

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields == initialValue

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 | x | y
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | 'o'  | 'o'  | 1 | 1
        ActivePlayer.PLAYER_TWO | '+'  | 'x'  | 'o'  | 'o'  | '+'  | 'x'  | '+'  | 'o'  | 'x'  | 0 | 2
        ActivePlayer.PLAYER_TWO | '+'  | 'x'  | 'o'  | '+'  | 'o'  | 'x'  | 'o'  | '+'  | 'x'  | 1 | 2
    }

    @Unroll
    def "Nach dem aktuellen Spieler #player ist als naechster Spieler #nextPlayer am Zug."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player

        when:
        sut.switchActivePlayer()

        then:
        sut.activePlayer == nextPlayer

        where:
        player                  || nextPlayer
        ActivePlayer.PLAYER_ONE || ActivePlayer.PLAYER_TWO
        ActivePlayer.PLAYER_TWO || ActivePlayer.PLAYER_ONE
    }

    @Unroll
    def "Der aktuelle Spielstand (Row) mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 || result
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_TWO | 'o'  | 'x'  | 'o'  | 'x'  | 'o'  | 'x'  | 'o'  | 'x'  | 'o'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'x'  | 'x'  | 'x'  | 'o'  | 'o'  | 'o'  || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | 'o'  | 'o'  | 'o'  | '+'  | '+'  | '+'  | 'o'  | 'o'  | 'o'  || GameState.PLAYER_TWO_WIN
        ActivePlayer.PLAYER_ONE | 'x'  | 'x'  | 'x'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | '+'  | '+'  | '+'  || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Column) mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 || result
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | 'o'  | '+'  | 'o'  | '+'  | 'o'  | '+'  | 'o'  | '+'  | 'o'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | 'o'  | 'x'  | 'o'  | 'o'  | 'x'  | 'o'  | 'o'  | 'x'  | 'o'  || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | 'o'  | '+'  | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | '+'  | 'o'  || GameState.PLAYER_TWO_WIN
        ActivePlayer.PLAYER_ONE | 'x'  | 'o'  | 'o'  | 'x'  | 'o'  | 'o'  | 'x'  | 'o'  | 'o'  || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | '+'  | 'o'  | 'o'  | '+'  || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Diagonal) mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = [
                new Koordinate(0, 0): x0y0 as Character, new Koordinate(1, 0): x1y0 as Character, new Koordinate(2, 0): x2y0 as Character,
                new Koordinate(0, 1): x0y1 as Character, new Koordinate(1, 1): x1y1 as Character, new Koordinate(2, 1): x2y1 as Character,
                new Koordinate(0, 2): x0y2 as Character, new Koordinate(1, 2): x1y2 as Character, new Koordinate(2, 2): x2y2 as Character,
        ]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | x0y0 | x1y0 | x2y0 | x0y1 | x1y1 | x2y1 | x0y2 | x1y2 | x2y2 || result
        ActivePlayer.PLAYER_ONE | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  | 'o'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_TWO | 'x'  | 'o'  | 'x'  | 'o'  | 'o'  | 'o'  | 'x'  | 'o'  | 'x'  || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | 'x'  | 'o'  | 'o'  | 'o'  | 'x'  | 'o'  | 'o'  | 'o'  | 'x'  || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | 'o'  | 'o'  | '+'  | 'o'  | '+'  | 'o'  | '+'  | 'o'  | 'o'  || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Full) #board wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = [
                new Koordinate(0, 0): '+' as Character, new Koordinate(1, 0): 'x' as Character, new Koordinate(2, 0): '+' as Character,
                new Koordinate(0, 1): '+' as Character, new Koordinate(1, 1): 'x' as Character, new Koordinate(2, 1): '+' as Character,
                new Koordinate(0, 2): 'x' as Character, new Koordinate(1, 2): '+' as Character, new Koordinate(2, 2): 'x' as Character,
        ]

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == GameState.DRAW
    }
}
