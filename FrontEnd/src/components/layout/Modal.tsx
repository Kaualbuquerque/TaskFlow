/* Styles */
import styles from "../styles/components/modal.module.css"

interface ModalProps {
    isOpen: boolean; // Indica se o modal está visível
    onClose: () => void; // Função para fechar o modal
    children: React.ReactNode; // Conteúdo dinâmico dentro do modal
}


function Modal({ isOpen, onClose, children }: ModalProps) {
    if (!isOpen) return null;
    
    return (
        <div className={styles.overlay} onClick={onClose}>
            <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
                {children}
                <button className={styles.closeButton} onClick={onClose}>
                    Close
                </button>
            </div>
        </div>
    )
}

export default Modal