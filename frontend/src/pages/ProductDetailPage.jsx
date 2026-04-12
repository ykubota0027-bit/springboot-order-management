import { Link, useParams } from "react-router-dom";

function ProductDetailPage({ products }) {
    const { id } = useParams();

    const product = products.find((p) => p.id === Number(id));

    if (!product) {
        return (
            <div>
                <h1>商品詳細</h1>
                <p>商品が見つかりませんでした。</p>
                <p>
                    <Link to="/products">商品一覧へ戻る</Link>
                </p>
            </div>
        );
    }

    return (
        <div>
            <h1>商品詳細</h1>

            <p>商品名: {product.name}</p>
            <p>価格: {product.price}円</p>
            <p>在庫: {product.stock}</p>
            <p>カテゴリ: {product.category}</p>

            <p>
                <Link to="/products">商品一覧へ戻る</Link>
            </p>
        </div>
    );
}

export default ProductDetailPage;