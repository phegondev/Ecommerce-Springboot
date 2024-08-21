import React, { useState, useEffect } from "react";
import ApiService from "../../service/ApiService";
import { useNavigate } from "react-router-dom";
import '../../style/adminCategory.css'

const AdminCategoryPage = () => {

    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();


    useEffect(()=>{
        fetchCategories();
    }, [])

    const fetchCategories = async()=>{
        try {
            const response = await ApiService.getAllCategory();
            setCategories(response.categoryList || []);
        } catch (error) {
            console.log("Error fetching category list",  error)
        }
    }

    const handleEdit = async (id) => {
        navigate(`/admin/edit-category/${id}`)
    }
    const handleDelete = async(id) => {
        const confirmed = window.confirm("Are your sure you want to delete this category? ")
        if(confirmed){
            try {
                await ApiService.deleteCategory(id);
                fetchCategories();
            } catch (error) {
                console.log("Error deleting category by id")
            }
        }
    }

    return(
        <div className="admin-category-page">
            <div className="admin-category-list">
                <h2>Categories</h2>
                <button onClick={()=> navigate('/admin/add-category')}>Add Category</button>
                <ul>
                    {categories.map((category) => (
                        <li key={category.id}>
                            <span>{category.name}</span>
                            <div className="admin-bt">
                                    <button className="admin-btn-edit" onClick={()=> handleEdit(category.id)}>Edit</button>
                                    <button  onClick={()=> handleDelete(category.id)}>Delete</button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default AdminCategoryPage;