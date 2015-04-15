package sudoku

class Sudoku {
    def sudokuBoard, logicalBoard

    Sudoku() {
        sudokuBoard = new Object[9][9]
        logicalBoard = new Boolean[9][9]

        for (Boolean[] row : logicalBoard)
            Arrays.fill(row, true)
    }

    def placeNumber(def entry, def row, def column){

        checkEntry(entry, row, column)
        sudokuBoard[row][column] = entry
        checkEntryLogic(entry, row, column)
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

    def checkEntryLogic(def entry, def row, def column){
        checkRowLogic(entry, row, column)
        checkColumnLogic(entry, row, column)
        checkBoxLogic(entry, row, column)
    }

    def checkRowLogic(def entry, def row, def column){
        for(i in 0..row){
            if(sudokuBoard[i][column] == entry)
                if(i != row ) {
                    logicalBoard[row][column] = false
                    return
                }
        }
    }

    def checkColumnLogic(def entry, def row, def column){
        for(i in 0..column){
            if(sudokuBoard[row][i] == entry)
                if(i != column ) {
                    logicalBoard[row][column] = false
                    return
                }
        }
    }

    def checkBoxLogic(def entry, def row, def column){
        for (i in row - 3..row) {
            for (j in column - 3..column) {
                if(sudokuBoard[i][j] == entry && (i != row && j!= column)) {
                    logicalBoard[row][column] = false
                    return
                }
            }
        }
    }

    def initializeBoard(){
        sudokuBoard[0][0] = 6
        sudokuBoard[0][2] = 8
        sudokuBoard[0][5] = 5
        sudokuBoard[1][0] = 3
        sudokuBoard[1][2] = 1
        sudokuBoard[1][8] = 7
        sudokuBoard[2][7] = 1
        sudokuBoard[4][0] = 9
        sudokuBoard[4][1] = 1
        sudokuBoard[4][3] = 5
        sudokuBoard[4][5] = 3
        sudokuBoard[4][7] = 8
        sudokuBoard[4][8] = 4
        sudokuBoard[6][1] = 2
        sudokuBoard[7][0] = 5
        sudokuBoard[7][6] = 4
        sudokuBoard[7][8] = 9
        sudokuBoard[8][3] = 3
        sudokuBoard[8][6] = 5
        sudokuBoard[8][8] = 8
    }
}
