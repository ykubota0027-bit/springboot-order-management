import { Link } from "react-router-dom";

function ProductCard({ product }) {
    return (
        <div
            style={{
                border: "1px solid #ccc",
                padding: "12px",
                marginBottom: "12px",
                borderRadius: "8px"
            }}
        >
            <h2>{product.name}</h2>
            <p>価格: {product.price}円</p>
            <p>在庫: {product.stock}</p>
            <p>カテゴリ: {product.category}</p>

            <Link to={`/products/${product.id}`}>詳細を見る</Link>
        </div>
    );
}

export default ProductCard;