package ticTacToe

import spock.lang.Specification
import spock.lang.Unroll
import ticTacToe.entity.ActivePlayer
import ticTacToe.entity.GameState

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
        sut.board.fields = fieldsInput as char[][]

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields == fieldsOutput as char[][]

        where:
        player                  | fieldsInput                                         | x | y || fieldsOutput
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']] | 0 | 0 || [['x', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']]
        ActivePlayer.PLAYER_TWO | [['x', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']] | 1 | 1 || [['x', 'o', 'o'], ['o', '+', 'o'], ['o', 'o', 'o']]
        ActivePlayer.PLAYER_ONE | [['x', 'o', 'o'], ['o', '+', 'o'], ['o', 'o', 'o']] | 0 | 2 || [['x', 'o', 'o'], ['o', '+', 'o'], ['x', 'o', 'o']]
        ActivePlayer.PLAYER_TWO | [['x', 'o', 'o'], ['o', '+', 'o'], ['x', 'o', 'o']] | 2 | 0 || [['x', 'o', '+'], ['o', '+', 'o'], ['x', 'o', 'o']]
        ActivePlayer.PLAYER_ONE | [['x', 'o', '+'], ['o', '+', 'o'], ['x', 'o', 'o']] | 2 | 2 || [['x', 'o', '+'], ['o', '+', 'o'], ['x', 'o', 'x']]
    }

    @Unroll
    def "Die nicht valide Eingabe (x=#x, y=#y) fuer Spieler #player wird nicht eingetragen."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player
        sut.board.fields = fields as char[][]

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields == fields as char[][]

        where:
        player                  | fields                                              | x  | y
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', '+', 'o'], ['o', 'o', 'o']] | -1 | 0
        ActivePlayer.PLAYER_TWO | [['+', 'x', 'o'], ['o', '+', 'x'], ['+', 'o', 'x']] | 0  | -1
        ActivePlayer.PLAYER_ONE | [['+', 'x', 'o'], ['+', 'o', 'x'], ['o', '+', 'x']] | -1 | -1
        ActivePlayer.PLAYER_TWO | [['o', 'o', 'o'], ['o', '+', 'o'], ['o', 'o', 'o']] | -3 | -5
        ActivePlayer.PLAYER_ONE | [['+', 'x', 'o'], ['o', '+', 'x'], ['+', 'o', 'x']] | -5 | -7
    }

    @Unroll
    def "Das bereits besetzte Feld (x=#x, y=#y) fuer Spieler #player wird nicht eingetragen."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.activePlayer = player
        sut.board.fields = fields as char[][]

        when:
        sut.enterMove(x, y)

        then:
        sut.board.fields == fields as char[][]

        where:
        player                  | fields                                              | x | y
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', '+', 'o'], ['o', 'o', 'o']] | 1 | 1
        ActivePlayer.PLAYER_TWO | [['+', 'x', '+'], ['o', '+', 'x'], ['x', 'o', 'x']] | 2 | 0
        ActivePlayer.PLAYER_TWO | [['+', 'x', 'o'], ['+', 'o', 'x'], ['o', '+', 'x']] | 1 | 2
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
    def "Der aktuelle Spielstand (Row) #fields mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = fields as char[][]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | fields                                              || result
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_TWO | [['o', 'x', 'o'], ['x', 'o', 'x'], ['o', 'x', 'o']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['x', 'x', 'x'], ['o', 'o', 'o']] || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | [['o', 'o', 'o'], ['+', '+', '+'], ['o', 'o', 'o']] || GameState.PLAYER_TWO_WIN
        ActivePlayer.PLAYER_ONE | [['x', 'x', 'x'], ['o', 'o', 'o'], ['o', 'o', 'o']] || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | [['o', 'o', 'o'], ['o', 'o', 'o'], ['+', '+', '+']] || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Column) #fields mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = fields as char[][]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | fields                                              || result
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | [['o', '+', 'o'], ['+', 'o', '+'], ['o', '+', 'o']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | [['o', 'x', 'o'], ['o', 'x', 'o'], ['o', 'x', 'o']] || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | [['o', '+', 'o'], ['o', '+', 'o'], ['o', '+', 'o']] || GameState.PLAYER_TWO_WIN
        ActivePlayer.PLAYER_ONE | [['x', 'o', 'o'], ['x', 'o', 'o'], ['x', 'o', 'o']] || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | [['o', 'o', '+'], ['o', 'o', '+'], ['o', 'o', '+']] || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Diagonal) #fields mit aktuellen Spieler #player wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = fields as char[][]
        sut.activePlayer = player

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == result

        where:
        player                  | fields                                              || result
        ActivePlayer.PLAYER_ONE | [['o', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_TWO | [['x', 'o', 'x'], ['o', 'o', 'o'], ['x', 'o', 'x']] || GameState.UNDECIDED
        ActivePlayer.PLAYER_ONE | [['x', 'o', 'o'], ['o', 'x', 'o'], ['o', 'o', 'x']] || GameState.PLAYER_ONE_WIN
        ActivePlayer.PLAYER_TWO | [['o', 'o', '+'], ['o', '+', 'o'], ['+', 'o', 'o']] || GameState.PLAYER_TWO_WIN
    }

    @Unroll
    def "Der aktuelle Spielstand (Full) #board wird korrekt bewertet."() {
        given:
        TicTacToe sut = new TicTacToe()
        sut.board.fields = [['+', 'x', '+'], ['+', 'x', '+'], ['x', '+', 'x']] as char[][]

        when:
        def gameState = sut.evaluateGame()

        then:
        gameState == GameState.DRAW
    }
}
