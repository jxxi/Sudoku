package sudoku

class Sudoku {
    def sudokuBoard, logicalBoard

    Sudoku(){
        sudokuBoard = new Object[9][9]
        logicalBoard = new Boolean[9][9]

        for(Boolean[] row: logicalBoard)
            Arrays.fill(row, true)
    }

    def placeNumber(def entry, def row, def column){

        checkEntry(entry, row, column)
        sudokuBoard[row][column] = entry
        checkPositionLogic(entry, row, column)
    }

    def clearPosition(def row, def column){
        checkEntry(0, row, column)
        sudokuBoard[row][column] = null
        logicalBoard[row][column] = true
    }

    def checkEntry(def entry, def row, def column){
        if(entry instanceof String || entry < 0 || entry > 9)
            throw new IllegalArgumentException()

        if(row < 0 || row > 9 || column < 0 || column > 9)
            throw new ArrayIndexOutOfBoundsException()
    }

    def checkPositionLogic(def entry, def row, def column){

        for(i in 0..row){
            if(sudokuBoard[i][column] == entry)
                if(i != row ) {
                    logicalBoard[row][column] = false
                    return
                }
        }

        for(i in 0..column){
            if(sudokuBoard[row][i] == entry)
                if(i != column ) {
                    logicalBoard[row][column] = false
                    return
                }
        }

        logicalBoard[row][column] = true
    }
}
