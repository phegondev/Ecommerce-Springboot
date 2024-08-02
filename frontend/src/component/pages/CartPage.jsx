import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";
import { useCart } from "../context/CartContext";
import '../../style/cart.css'

const CartPage = () => {
    const { cart, dispatch } = useCart();
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();


    const incrementItem = (product) => {
        dispatch({ type: 'INCREMENT_ITEM', payload: product });
    }

    const decrementItem = (product) => {

        const cartItem = cart.find(item => item.id === product.id);
        if (cartItem && cartItem.quantity > 1) {
            dispatch({ type: 'DECREMENT_ITEM', payload: product });
        } else {
            dispatch({ type: 'REMOVE_ITEM', payload: product });
        }
    }

    const totalPrice = cart.reduce((total, item) => total + item.price * item.quantity, 0);



    const handleCheckout = async () => {
        if (!ApiService.isAuthenticated()) {
            setMessage("You need to login first before you can place an order");
            setTimeout(() => {
                setMessage('')
                navigate("/login")
            }, 3000);
            return;
        }

        const orderItems = cart.map(item => ({
            productId: item.id,
            quantity: item.quantity
        }));

        const orderRequest = {
            totalPrice,
            items: orderItems,
        }

        try {
            const response = await ApiService.createOrder(orderRequest);
            setMessage(response.message)

            setTimeout(() => {
                setMessage('')
            }, 5000);

            if (response.status === 200) {
                dispatch({ type: 'CLEAR_CART' })
            }

        } catch (error) {
            setMessage(error.response?.data?.message || error.message || 'Failed to place an order');
            setTimeout(() => {
                setMessage('')
            }, 3000);

        }

    };


    return (
        <div className="cart-page">
            <h1>Cart</h1>
            {message && <p className="response-message">{message}</p>}

            {cart.length === 0 ? (
                <p>Your cart is empty</p>
            ) : (
                <div>
                    <ul>
                        {cart.map(item => (
                            <li key={item.id}>
                                <img src={item.imageUrl} alt={item.name} />
                                <div>
                                    <h2>{item.name}</h2>
                                    <p>{item.description}</p>
                                    <div className="quantity-controls">
                                        <button onClick={()=> decrementItem(item)}>-</button>
                                        <span>{item.quantity}</span>
                                        <button onClick={()=> incrementItem(item)}>+</button>
                                    </div>
                                    <span>${item.price.toFixed()}</span>
                                </div>
                            </li>
                        ))}
                    </ul>
                    <h2>Total: ${totalPrice.toFixed(2)}</h2>
                    <button className="checkout-button" onClick={handleCheckout}>Checkout</button>
                </div>
            )}
        </div>
    )
}

export default CartPage;
