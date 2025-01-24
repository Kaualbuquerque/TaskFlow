import Navbar from "../layout/Navbar";
import logo from "../../assets/icons/logo.png";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

/* Styles */
import styles from "../styles/pages/register.module.css"

function Register() {
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

        // Redireciona para a página /login
        navigate("/login");
    };

    return (
        <div className={styles.page}>
            <Navbar style="navbarHorizontal" btnText="Login" icon={logo} btnAddress="/login"/>

            <div className={styles.main}>
                <div className={styles.registerModal}>
                    <h2>Create a New Account</h2>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            name="username"
                            id="username"
                            value={formData.username}
                            onChange={handleChange}
                            placeholder="Enter your username"
                            required
                        />
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            name="email"
                            id="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="Enter your email"
                            required
                        />
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            name="password"
                            id="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Enter your password"
                            required
                        />
                        <button type="submit">Create account</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Register;
