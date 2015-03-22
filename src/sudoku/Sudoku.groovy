package sudoku

class Sudoku {
    def sudokuBoard

    Sudoku(){
        sudokuBoard = new Object[9][9]
    }

    def placeNumber(def entry, def row, def column){

        checkEntry(entry, row, column)
        sudokuBoard[row][column] = entry
    }

    def checkEntry(def entry, def row, def column){
        if(entry instanceof String || entry < 0 || entry > 9)
            throw new IllegalArgumentException()

        else if(row < 0 || row > 9 || column < 0 || column > 9)
            throw new ArrayIndexOutOfBoundsException()
    }
}
