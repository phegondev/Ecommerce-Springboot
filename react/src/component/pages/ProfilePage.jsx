import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";
import '../../style/profile.css';
import Pagination from "../common/Pagination";

const ProfilePage = () => {

    const [userInfo, setUserInfo] = useState(null);
    const [error, setError] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 5;
    const navigate = useNavigate();


    useEffect(() => {

        fetchUserInfo();
    }, []);
    const fetchUserInfo = async () => {

        try {
            const response = await ApiService.getLoggedInUserInfo();
            setUserInfo(response.user);
        } catch (error) {
            setError(error.response?.data?.message || error.message || 'Unable to fetch user info');
        }
    }

    if (!userInfo) {
        return <div>Loading...</div>
    }

    const handleAddressClick = () => {
        navigate(userInfo.address ? '/edit-address' : '/add-address');
    }

    const orderItemList = userInfo.orderItemList || [];

    const totalPages = Math.ceil(orderItemList.length / itemsPerPage);

    const paginatedOrders = orderItemList.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
    );




    return (
        <div className="profile-page">
            <h2>Welcome {userInfo.name}</h2>

            {error ? (
                <p className="error-message">{error}</p>
            ) : (
                <div>
                    <p><strong>Name: </strong>{userInfo.name}</p>
                    <p><strong>Email: </strong>{userInfo.email}</p>
                    <p><strong>Phone Number: </strong>{userInfo.phoneNumber}</p>

                    <div>
                        <h3>Address</h3>
                        {userInfo.address ? (
                            <div>
                                <p><strong>Street: </strong>{userInfo.address.street}</p>
                                <p><strong>City: </strong>{userInfo.address.city}</p>
                                <p><strong>State: </strong>{userInfo.address.state}</p>
                                <p><strong>Zip Code: </strong>{userInfo.address.zipCode}</p>
                                <p><strong>Country: </strong>{userInfo.address.country}</p>
                            </div>
                        ) : (
                            <p>No Address information available</p>
                        )}
                        <button className="profile-button" onClick={handleAddressClick}>
                            {userInfo.address ? "Edit Address" : "Add Address"}
                        </button>
                    </div>
                    <h3>Order History</h3>
                    <ul>
                        {paginatedOrders.map(order => (
                            <li key={order.id}>
                                <img src={order.product?.imageUrl} alt={order.product.name} />
                                <div>
                                    <p><strong>Name: </strong>{order.product.name}</p>
                                    <p><strong>Status: </strong>{order.status}</p>
                                    <p><strong>Quantity: </strong>{order.quantity}</p>
                                    <p><strong>Price: </strong>{order.price.toFixed(2)}</p>
                                </div>
                            </li>
                        ))}
                    </ul>
                    <Pagination
                    currentPage={currentPage}
                    totalPages={totalPages}
                    onPageChange={(page)=> setCurrentPage(page)}/>
                </div>
            )}
        </div>
    )
}

export default ProfilePage;