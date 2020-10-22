import React, { useState} from 'react';
import axios from 'axios';

const PATH = "http://localhost:8080/banco-gerencia/contas";
const Debitar = () => {

    const [conta1, setConta1] = useState();
    const [conta2, setConta2] = useState();    
    const [valor, setValor] = useState();

    const loadData = async () => {
        axios.get(`${PATH}/transferir/${conta1}/${conta2}/${valor}`).then(({data}) => {
			alert(`Foi transferido um valor de R$${valor} da conta ${conta1} para ${conta2}`)
		}).catch((err) => alert(`Não foi possível realizar a operação, verifique se a conta existe e o valor é válido.`));
    };

    return( 
        <div>
            <h3>Transferir</h3>
            <div>
                <label htmlFor="conta">Conta Pagante: </label>
                <input type="number" placeholder="Conta" onChange={(e) => setConta1(e.target.value)}/>
                {" "}
                <label htmlFor="valor">Valor: </label>
                <input type="number" placeholder="Valor" onChange={(e) => setValor(e.target.value)}/>
                {" "}
                <label htmlFor="conta">Conta Favorecido: </label>
                <input type="number" placeholder="Conta" onChange={(e) => setConta2(e.target.value)}/>
            </div>
            <div className="content-center">
                <div className="enter-button" onClick={() => loadData()}>Confirmar</div>
            </div>
        </div>
    )
}

export default Debitar;