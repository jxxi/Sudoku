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

    def "placeNumber places a valid number at a valid specified position"(){
        sudoku.placeNumber(1, 0, 0)
        expect:
        sudoku.sudokuBoard[0][0] == 1
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

}