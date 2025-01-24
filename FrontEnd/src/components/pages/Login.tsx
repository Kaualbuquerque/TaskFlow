import Navbar from "../layout/Navbar";
import logo from "../../assets/icons/logo.png";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

/* Styles */
import styles from "../styles/pages/login.module.css"

function Login() {
    const navigate = useNavigate(); // Hook para navegação
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
    });

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e: any) => {
        e.preventDefault(); // Previne o comportamento padrão do formulário

        // Simulação de envio de dados ou lógica de validação
        console.log("Formulário enviado com os dados:", formData);

        navigate("/lists");
    };

    return (
        <div className={styles.page}>
            <Navbar style="navbarHorizontal" btnText="Register" icon={logo} btnAddress="/register"/>

            <div className={styles.main}>
                <div className={styles.loginModal}>
                    <h2>Login to your account</h2>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            name="email"
                            id="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="Email"
                            required
                        />
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            name="password"
                            id="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Password"
                            required
                        />
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
