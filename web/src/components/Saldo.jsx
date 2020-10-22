import React, {useState} from 'react';
import axios from 'axios';

const PATH = "http://localhost:8080/banco-gerencia/contas";
const Saldo = () => {

    const [conta, setConta] = useState();
    const [valor, setValor] = useState();

    const loadData = async () => {
        axios.get(`${PATH}/saldo/${conta}`).then(({data}) => {
			setValor(data);
		}).catch((err) => alert(`Não foi possível realizar a operação, verifique se o número da conta existe.`));
    };

    return( 
        <div>
            <h3>Ver saldo</h3>
            <div>
                <label htmlFor="conta">Conta: </label>
                <input type="number" placeholder="Conta" onChange={(e) => setConta(e.target.value)}/>
            </div>
            <div className="content-center">
                <div className="enter-button" onClick={() => loadData()}>Confirmar</div>
            </div>
            { valor >= 0 ?
                (
                    <div className="content-center">
                        <div>{`Saldo da conta ${conta} é de R$ ${valor}`}</div>
                    </div>
                ) : null
            }
        </div>
    )
}

export default Saldo;