import "./App.css";
import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';

import {Row, Col, Button, message} from "antd";
import {DeleteFilled, SaveFilled} from "@ant-design/icons";
import CreateCarModal from "./CreateCarModal";

import {AgGridReact} from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';


const App = () => {

    const [rowData, setRowData] = useState([]);
    const gridRef = useRef(null);

    const [columnDefs] = useState([
        {field: "id", editable: false},
        {field: "maker", editable: true},
        {field: "model", editable: true},
        {field: "price", editable: true},
    ]);


    useEffect(() => {
        axios.get('http://localhost:8080/car')
            .then((resp) => {
                const data = resp.data;
                if (!data.success) {
                    message.error(data.message);
                }
                setRowData(data.content);
            })
    }, []);

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
            .then((resp) => {
                const data = resp.data;
                if (data.success) {
                    message.success(data.message);
                } else {
                    message.error(data.message);
                }
            })
    };

    const onBtDeleteRow = e => {
        const selectedNodes = gridRef.current.api.getSelectedNodes()
        const selectedData = selectedNodes.map(node => node.data)
        // console.log(selectedData);
        console.log(selectedData[0].id);
        axios.delete(`http://localhost:8080/car/${selectedData[0].id}`)
            .then((resp) => {
                const data = resp.data;
                if (data.success) {
                    axios.get('http://localhost:8080/car')
                        .then((resp) => {
                            setRowData(resp.data.content)
                        }).then(() => {
                            message.success(data.message);
                        }
                    )
                } else {
                    message.error(data.message);
                }
            })
    };


    return (
        <div className="ag-theme-alpine" style={{height: 1000, width: 800}}>
            <h1 className="page-title">Car Data-Grid</h1>
            <Row className="button-group">
                <Col span={8}>
                    <CreateCarModal></CreateCarModal>
                </Col>
                <Col span={8}>
                    <Button icon={<SaveFilled />} onClick={onBtSave}>Save Selected Car</Button>
                </Col>
                <Col span={8}>
                    <Button icon={<DeleteFilled />} onClick={onBtDeleteRow}>Delete Selected Car</Button>
                </Col>
            </Row>
            <AgGridReact
                ref={gridRef}
                rowData={rowData}
                columnDefs={columnDefs}
                rowSelection="single"
            >
            </AgGridReact>
        </div>
    );
};

export default App;