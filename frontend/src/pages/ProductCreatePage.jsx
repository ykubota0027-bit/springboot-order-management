import { Link, useNavigate } from "react-router-dom";
import ProductForm from "../components/ProductForm";

function ProductCreatePage({ onAddProduct }) {
    const navigate = useNavigate();

    const handleSubmit = (formData) => {
        onAddProduct(formData);
        navigate("/products");
    };

    return (
        <div>
            <h1>商品登録</h1>

            <p>
                <Link to="/">ホームへ戻る</Link>
            </p>
            <p>
                <Link to="/products">商品一覧へ</Link>
            </p>

            <ProductForm onSubmit={handleSubmit} />
        </div>
    );
}

export default ProductCreatePage;