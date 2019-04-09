package finkmoritz.conwaysgameoflifegame.config

import android.widget.TableRow
import android.widget.Spinner

class OnCellsSelectedListener(_cellsSpinner : Spinner, _rows : List<TableRow>, _spinners : List<Spinner>) :
        OnCellsOrRulesSelectedListener(_cellsSpinner,_rows,_spinners)