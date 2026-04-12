import { Link } from "react-router-dom";
import ProductCard from "../components/ProductCard";

function ProductListPage({ products }) {
    return (
        <div>
            <h1>商品一覧</h1>

            <p>
                <Link to="/">ホームへ戻る</Link>
            </p>
            <p>
                <Link to="/products/new">新規登録</Link>
            </p>

            {products.length === 0 ? (
                <p>商品がありません。</p>
            ) : (
                products.map((product) => (
                    <ProductCard key={product.id} product={product} />
                ))
            )}
        </div>
    );
}

export default ProductListPage;