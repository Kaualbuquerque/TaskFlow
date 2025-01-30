/* Styles */
import styles from "../styles/pages/lists.module.css";

/* Icons */
import logo from "../../assets/icons/logo.png";

/* Components */
import Navbar from "../layout/Navbar";
import ListBanner from "../layout/ListBanner";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

interface ListData {
    id: number;
    name: string;
}

function Lists() {
    const [data, setData] = useState<ListData[]>([]); // Inicializado como null para verificar posteriormente
    const [loading, setLoading] = useState(true); // Estado de carregamento

    useEffect(() => {
        const getAPI = async () => {
            try {
                const response = await fetch("http://localhost:8080/list/user/3", { method: "GET" });
                if (!response.ok) {
                    throw new Error(`Erro: ${response.status} - ${response.statusText}`);
                }
                const data = await response.json();
                setData(data);
                console.log(data)
            } catch (error) {
                console.error("Erro ao buscar dados:", error);
            } finally {
                setLoading(false); // Carregamento finalizado
            }
        };

        getAPI();
    }, []);

    return (
        <div className={styles.listPage}>
            <Navbar style="navbarVertical" icon={logo} btnText="">
                <ul>
                    {data.map((item) => (
                        <li key={item.id}>
                            <Link to="/tasks">
                                <p>{item.name}</p>
                            </Link>
                        </li>
                    ))}
                </ul>
            </Navbar>

            <div className={styles.lists}>
                {loading && <p>Carregando...</p>}
                {data && (
                    <>
                        {data.map((item) => (
                            <ListBanner key={item.id} address={`/tasks`} name={item.name} />
                        ))}
                        <ListBanner type="add" />
                    </>
                )}
            </div>
        </div>
    );
}

export default Lists;
