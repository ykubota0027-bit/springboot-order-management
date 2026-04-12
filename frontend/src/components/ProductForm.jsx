import { useState } from "react";

function ProductForm({ onSubmit }) {
    const [form, setForm] = useState({
        name: "",
        price: "",
        stock: "",
        category: ""
    });

    const [errors, setErrors] = useState({
        name: "",
        price: "",
        stock: "",
        category: ""
    });

    const validate = (targetForm) => {
        const newErrors = {
            name: "",
            price: "",
            stock: "",
            category: ""
        };

        if (!targetForm.name.trim()) {
            newErrors.name = "商品名は必須です";
        }

        if (!targetForm.price) {
            newErrors.price = "価格は必須です";
        } else if (Number(targetForm.price) <= 0) {
            newErrors.price = "価格は1以上で入力してください";
        }

        if (!targetForm.stock) {
            newErrors.stock = "在庫数は必須です";
        } else if (Number(targetForm.stock) < 0) {
            newErrors.stock = "在庫数は0以上で入力してください";
        }

        if (!targetForm.category.trim()) {
            newErrors.category = "カテゴリは必須です";
        }

        return newErrors;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;

        const updatedForm = {
            ...form,
            [name]: value
        };

        setForm(updatedForm);

        setErrors((prev) => ({
            ...prev,
            [name]: ""
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const newErrors = validate(form);
        setErrors(newErrors);

        const hasError = Object.values(newErrors).some((error) => error !== "");
        if (hasError) return;

        onSubmit(form);

        setForm({
            name: "",
            price: "",
            stock: "",
            category: ""
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>商品名</label>
                <br />
                <input
                    type="text"
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                />
                {errors.name && <p>{errors.name}</p>}
            </div>

            <div>
                <label>価格</label>
                <br />
                <input
                    type="number"
                    name="price"
                    value={form.price}
                    onChange={handleChange}
                />
                {errors.price && <p>{errors.price}</p>}
            </div>

            <div>
                <label>在庫数</label>
                <br />
                <input
                    type="number"
                    name="stock"
                    value={form.stock}
                    onChange={handleChange}
                />
                {errors.stock && <p>{errors.stock}</p>}
            </div>

            <div>
                <label>カテゴリ</label>
                <br />
                <input
                    type="text"
                    name="category"
                    value={form.category}
                    onChange={handleChange}
                />
                {errors.category && <p>{errors.category}</p>}
            </div>

            <button type="submit">登録</button>
        </form>
    );
}

export default ProductForm;