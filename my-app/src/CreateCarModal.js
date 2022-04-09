import React, {useState} from 'react';
import Modal from 'react-modal'
import axios from "axios";
import {Button, message} from "antd";
import {FolderAddFilled} from "@ant-design/icons";


const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
    },
};


const CreateCarModal = () => {
    let subtitle;
    const [modalIsOpen, setIsOpen] = useState(false);

    const openModal = () => {
        setIsOpen(true);
    }

    const afterOpenModal = () => {
        subtitle.style.color = '#f00';
    }

    const closeModal = () => {
        setIsOpen(false);
    }

    const createNewCar = () => {
        axios.post('http://localhost:8080/car',
            {
                id: document.getElementById('id-input').value,
                maker: document.getElementById('maker-input').value,
                model: document.getElementById('model-input').value,
                price: document.getElementById('price-input').value
            })
            .then((resp) => {
                console.log(resp);
                const data = resp.data;
                if (data.success) {
                    console.log(data.message);
                    window.location.reload();
                } else {
                    console.log(data.message);
                    message.error(data.message)
                }
            })
    }

    return (
        <div>
            <Button icon={<FolderAddFilled/>} onClick={openModal}>Create New Car</Button>
            <Modal
                isOpen={modalIsOpen}
                onAfterOpen={afterOpenModal}
                onRequestClose={closeModal}
                style={customStyles}
                contentLabel="Example Modal"
            >
                <h2 ref={(_subtitle) => (subtitle = _subtitle)}>
                    Input Information for a new car
                </h2>

                <form>
                    CarID <input id="id-input"/><br></br>
                    Maker <input id="maker-input"/><br></br>
                    Model <input id="model-input"/><br></br>
                    Price <input id="price-input"/><br></br>
                    <button id="test" type="button" onClick={createNewCar}>Save</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick={closeModal}>Close</button>
                </form>

            </Modal>
        </div>
    );
}

export default CreateCarModal;