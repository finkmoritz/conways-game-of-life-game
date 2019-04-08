package finkmoritz.conwaysgameoflifegame.config

import android.widget.TableRow
import android.widget.Spinner

class onCellsSelectedListener(val _cellsSpinner : Spinner, val _rows : List<TableRow>, val _spinners : List<Spinner>) :
        onCellsOrRulesSelectedListener(_cellsSpinner,_rows,_spinners)