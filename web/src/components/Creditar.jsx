import React, {useState} from 'react';
import axios from 'axios';

const PATH = "http://localhost:8080/banco-gerencia/contas";
const Creditar = () => {

    const [conta, setConta] = useState();
    const [valor, setValor] = useState();

    const loadData = async () => {
        axios.get(`${PATH}/creditar/${conta}/${valor}`).then(({data}) => {
			alert(`Foi creditado um valor de R$${valor} na conta ${conta}`)
		}).catch((err) => alert(`Não foi possível realizar a operação, verifique se a conta existe e o valor é válido.`));
    };

    return( 
        <div>
            <h3>Creditar</h3>
            <div>
            <label htmlFor="conta">Conta: </label>
                <input type="number" placeholder="Conta" onChange={(e) => setConta(e.target.value)}/>
                {" "}
                <label htmlFor="valor">Valor: </label>
                <input type="number" placeholder="Valor" onChange={(e) => setValor(e.target.value)}/>
            </div>
            <div className="content-center">
                <div className="enter-button" onClick={() => loadData()}>Confirmar</div>
            </div>
        </div>
    )
}

export default Creditar;