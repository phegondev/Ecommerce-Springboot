import React, { useState } from "react";
import ApiService from "../../service/ApiService";
import { useNavigate } from "react-router-dom";
import '../../style/addCategory.css'

const AddCategory = () => {
    const [name, setName] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await ApiService.createCategory({name});
            if (response.status === 200) {
                setMessage(response.message);
                setTimeout(()=>{
                    setMessage('');
                    navigate("/admin/categories")
                }, 3000)
            }
        } catch (error) {
            setMessage(error.response?.data?.message || error.message || "Failed to save a category")
        }
    }

    return(
        <div className="add-category-page">
            {message && <p className="message">{message}</p>}
            <form onSubmit={handleSubmit} className="category-form">
                <h2>Add Category</h2>
                <input type="text"
                placeholder="Category Name"
                value={name}
                onChange={(e)=> setName(e.target.value)} />

                <button type="submit">Add</button>
            </form>
        </div>
    )
}

export default AddCategory;