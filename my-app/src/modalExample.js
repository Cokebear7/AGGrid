import React, {useEffect, useState} from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-modal'
import axios from "axios";

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

// Make sure to bind modal to your appElement (https://reactcommunity.org/react-modal/accessibility/)
// Modal.setAppElement('#createForm');

const ModalExample = () => {
    let subtitle;
    const [modalIsOpen, setIsOpen] = useState(false);


    const openModal = () => {
        setIsOpen(true);
    }

    const afterOpenModal = () => {
        // references are now sync'd and can be accessed.
        subtitle.style.color = '#f00';
    }

    const closeModal = () => {
        setIsOpen(false);
    }

    const createNewCar = () => {
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
            }).then((resp) => {
            console.log(resp);
            const data = resp.data;
            if (data.success) {
                console.log(data.message);
                window.location.reload();
            } else {
                setIsOpen(false);
                console.log(data.message);
                alert(data.message)
            }
        })


        //
    }

    return (
        <div>
            <button onClick={openModal}>Create New Car</button>
            <Modal
                isOpen={modalIsOpen}
                onAfterOpen={afterOpenModal}
                onRequestClose={closeModal}
                style={customStyles}
                contentLabel="Example Modal"
            >
                <h2 ref={(_subtitle) => (subtitle = _subtitle)}>Input Information for a new car</h2>

                {/*<div>Input Information for a new car</div>*/}
                <form>
                    ID <input id="id-input"/><br></br>
                    Maker <input id="maker-input"/><br></br>
                    Model <input id="model-input"/><br></br>
                    Price <input id="price-input"/><br></br>
                    <button id="test" type="button" onClick={createNewCar}>Save</button>
                    <button onClick={closeModal}>Close</button>
                </form>
            </Modal>
        </div>
    );
}

// ReactDOM.render(<ModalExample />, ModalExample);

export default ModalExample;