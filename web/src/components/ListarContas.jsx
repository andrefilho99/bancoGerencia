import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Table} from 'react-bootstrap';

const PATH = "http://localhost:8080/banco-gerencia/contas";
const ListarContas = () => {

    const [contas, setContas] = useState([]);

    const loadData = async () => {
        axios.get(`${PATH}/`).then(({data}) => {
			setContas(data);
		}).catch((err) => console.log(err));
    };

    useEffect(() => {
        loadData();
    }, []);

    return( 
        <div className="bank-menu">
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Contas</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                {contas.map((e,i) => {
                    return (
                        <tr key={i}>
                            <td>{e.numConta}</td>
                            <td>{e.ativo ? "✔️" : "❌" }</td>
                        </tr>
                    )
                })}
                </tbody>
            </Table>
        </div>
    )
}

export default ListarContas;