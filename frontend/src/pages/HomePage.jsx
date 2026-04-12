import { Link } from "react-router-dom";

function HomePage() {
    return (
        <div>
            <h1>商品管理アプリ</h1>
            <p>React Router を使った複数画面構成の練習用アプリです。</p>

            <nav>
                <ul>
                    <li>
                        <Link to="/products">商品一覧へ</Link>
                    </li>
                    <li>
                        <Link to="/products/new">商品登録へ</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

export default HomePage;