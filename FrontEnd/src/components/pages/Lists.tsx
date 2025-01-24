/* Styles */
import styles from "../styles/pages/lists.module.css";

/* Icons */
import logo from "../../assets/icons/logo.png";

/* Components */
import Navbar from "../layout/Navbar";
import ListBanner from "../layout/ListBanner";
import { useEffect, useState } from "react";

interface ListData {
    name: string;
}

function Lists() {
    const [data, setData] = useState<ListData | null>(null); // Inicializado como null para verificar posteriormente
    const [loading, setLoading] = useState(true); // Estado de carregamento

    useEffect(() => {
        const getAPI = async () => {
            try {
                const response = await fetch("http://localhost:8080/list/1", { method: "GET" });
                if (!response.ok) {
                    throw new Error(`Erro: ${response.status} - ${response.statusText}`);
                }
                const data = await response.json();
                setData(data);
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
                    <li>List 1</li>
                    <li>List 2</li>
                    <li>List 3</li>
                    +
                </ul>
            </Navbar>

            <div className={styles.lists}>
                {loading && <p>Carregando...</p>}
                {data && (
                    <>
                        <ListBanner address={"/tasks"} name={data.name || "Sem Nome"} />
                        <ListBanner type="add" />
                    </>
                )}
            </div>
        </div>
    );
}

export default Lists;
