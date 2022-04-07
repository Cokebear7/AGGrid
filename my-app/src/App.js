import React, {useState, useEffect, useRef, useCallback, useMemo} from 'react';
import {AgGridReact} from 'ag-grid-react';
import axios from 'axios';
import ModalExample from "./modalExample";
import Modal from 'react-modal'

import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';

const App = () => {
    // const [rowData] = useState([
    //   {make: "Toyota", model: "Celica", price: 35000},
    //   {make: "Ford", model: "Mondeo", price: 32000},
    //   {make: "Porsche", model: "Boxter", price: 72000}
    // ]);

    const [rowData, setRowData] = useState([]);
    const gridRef = useRef(null);

    const [columnDefs] = useState([
        {field: "id", editable: false},
        {field: "maker", editable: true},
        {field: "model", editable: true},
        {field: "price", editable: true},
    ]);

    // const defaultColDef = useMemo(() => {
    //     return {
    //         flex: 1,
    //         minWidth: 100,
    //         editable: true,
    //     };
    // }, []);

    // useEffect(() => {
    //     fetch('https://www.ag-grid.com/example-assets/small-row-data.json')
    //         .then(
    //             result => result.json()
    //         )
    //         .then(rowData => setRowData(rowData))
    // }, []);

    useEffect(() => {
        axios.get('http://localhost:8080/car')
            .then((resp) => {
                console.log(resp)
                console.log(resp.data)
                setRowData(resp.data)
            })
    }, []);

    const onButtonClick = e => {
        const selectedNodes = gridRef.current.api.getSelectedNodes()
        const selectedData = selectedNodes.map(node => node.data)
        const selectedDataStringPresentation = selectedData.map(node => `${node.id} ${node.maker} ${node.model} ${node.price}`).join(', ')
        alert(`Selected nodes: ${selectedDataStringPresentation}`)
    };

    // const onCellClicked = e => {
    //     const selectedNodes = gridRef.current.api.getSelectedNodes()
    //     const selectedData = selectedNodes.map(node => node.data)
    //     console.log(selectedData)
    //     // const selectedDataStringPresentation = selectedData.map(node => `${node.make} ${node.model} ${node.price}`).join(', ')
    //     // alert(`Selected nodes: ${selectedData}`)
    // }

    const onBtStopEditing = e => {
        gridRef.current.api.stopEditing();
    }

    const onBtSave = e => {
        gridRef.current.api.stopEditing();
        const selectedNodes = gridRef.current.api.getSelectedNodes()
        const selectedData = selectedNodes.map(node => node.data)
        console.log(selectedData[0])
        axios.put('http://localhost:8080/car',
            {
                id: selectedData[0].id,
                maker: selectedData[0].maker,
                model: selectedData[0].model,
                price: selectedData[0].price
            })
    };

    const onBtDeleteRow = e => {
        const selectedNodes = gridRef.current.api.getSelectedNodes()
        const selectedData = selectedNodes.map(node => node.data)
        // console.log(selectedData);
        console.log(selectedData[0].id);
        axios.delete(`http://localhost:8080/car/${selectedData[0].id}`)
            .then(() => {
                axios.get('http://localhost:8080/car')
                    .then((resp) => {
                        console.log(resp)
                        console.log(resp.data)
                        setRowData(resp.data)
                    })
                //     .then(() => {
                //         alert('delete success')
                //     }
                // )

            })
    };

    function createNewCar() {
        // console.log(document.getElementById('id-input').value)
        // console.log(document.getElementById('maker-input').value)
        // console.log(document.getElementById('model-input').value)
        // console.log(document.getElementById('price-input').value)
        axios.post('http://localhost:8080/car',
            {
                id: document.getElementById('id-input').value,
                maker: document.getElementById('maker-input').value,
                model: document.getElementById('model-input').value,
                price: document.getElementById('price-input').value
            })
            .then(() => {
                axios.get('http://localhost:8080/car')
                    .then((resp) => {
                        console.log(resp)
                        console.log(resp.data)
                        setRowData(resp.data)
                    })
            })
        // setIsOpen(false);
        // window.location.reload()
    }


    // const onBtStartEditing = useCallback((key, char, pinned) => {
    //     gridRef.current.api.setFocusedCell(0, 'make', pinned);
    //     gridRef.current.api.startEditingCell({
    //         rowIndex: 0,
    //         colKey: 'make',
    //         // set to 'top', 'bottom' or undefined
    //         rowPinned: pinned,
    //         key: key,
    //         charPress: char,
    //     });
    // }, []);

    return (
        <div className="ag-theme-alpine" style={{height: 1000, width: 1000}}>
            <button onClick={onButtonClick}>Get selected rows</button>
            {/*<button onClick={onBtStartEditing}>edit (0)</button>*/}
            <button onClick={onBtStopEditing}>stop ()</button>
            <button onClick={onBtSave}>Save Changes</button>
            <button onClick={onBtDeleteRow}>Delete Selected Row</button>
            <ModalExample></ModalExample>
            <AgGridReact
                ref={gridRef}
                rowData={rowData}
                columnDefs={columnDefs}
                // defaultColDef={defaultColDef}
                // onCellClicked={onCellClicked}
                rowSelection="single"
            >
            </AgGridReact>
        </div>
    );
};

export default App;