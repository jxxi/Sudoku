package sudoku

import spock.lang.*

class SudokuTest extends Specification{
    Sudoku sudoku
    def sudokuBoard

    def setup(){
        sudoku = new Sudoku()
        sudokuBoard  = new Object[9][9]
    }

    def "canary test"(){
        expect:
        true
    }

    def "checkEntry throws IllegalArgumentException if entry is not between 0 and 9 and the entry is not placed on board"(){
        when:
        sudoku.checkEntry(-1, 0, 0)
        then:
        thrown IllegalArgumentException
        sudoku.sudokuBoard[0][0] != -1
    }

    def "checkEntry throws IllegalArgumentException if entry is not a number and the entry is not placed on board"(){
        when:
        sudoku.checkEntry("a", 0, 0)
        then:
        thrown IllegalArgumentException
        sudoku.sudokuBoard[0][0] != "a"
    }

    def "checkEntry throws OutOfBoundsException if row is out of bounds"(){
        when:
        sudoku.checkEntry(1, 10, 0)
        then:
        thrown ArrayIndexOutOfBoundsException
    }

    def "checkEntry throws OutOfBoundsException if column is out of bounds"(){
        when:
        sudoku.checkEntry(1, 0, 10)
        then:
        thrown ArrayIndexOutOfBoundsException
    }

    def "placeNumber places a valid number at a valid specified position"(){
        sudoku.placeNumber(1, 0, 0)
        expect:
        sudoku.sudokuBoard[0][0] == 1
    }

    def "clearPosition clears a position on the board at a valid position"(){
        sudoku.placeNumber(1, 0, 0)
        sudoku.clearPosition(0, 0)
        expect:
        sudoku.sudokuBoard[0][0] == null
    }

    def "clearPosition leaves an already empty position empty"(){
        sudoku.sudokuBoard[0][0] = null
        sudoku.clearPosition(0, 0)
        expect:
        sudoku.sudokuBoard[0][0] == null
    }

    def "checkColumnLogic sets a position in logicalBoard to false if the number entered is repeated in column"(){
        sudoku.sudokuBoard[0][0] = 1
        sudoku.checkColumnLogic(1, 0, 1)
        expect:
        sudoku.logicalBoard[0][1] == false
    }

    def "checkRowLogic sets a position in logicalBoard to false if the number entered is repeated in row"(){
        sudoku.sudokuBoard[0][0] = 1
        sudoku.checkRowLogic(1, 1, 0)
        expect:
        sudoku.logicalBoard[1][0] == false
    }

    def "checkBoxLogic sets a position in logicalBoard to false if the number entered is repeated within the same square"(){
        sudoku.sudokuBoard[3][3] = 1
        sudoku.sudokuBoard[4][4] = 1
        sudoku.checkBoxLogic(1, 4, 4)
        expect:
        sudoku.logicalBoard[4][4] == false
    }

    def "isFull returns false if sudokuBoard is not full"(){
        sudoku.initializeBoard()
        expect:
        !sudoku.isFull()
    }

    def "userWon returns false if the user did not win"(){
        sudoku.initializeBoard()
        expect:
        !sudoku.userWon()
    }
}
