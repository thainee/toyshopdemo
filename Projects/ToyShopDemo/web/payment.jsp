<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Thanh toán</title>
        <!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>-->
        <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>
        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>::-webkit-scrollbar {
                width: 8px;
            }
            /* Track */
            ::-webkit-scrollbar-track {
                background: #f1f1f1;
            }

            /* Handle */
            ::-webkit-scrollbar-thumb {
                background: #888;
            }

            /* Handle on hover */
            ::-webkit-scrollbar-thumb:hover {
                background: #555;
            }
            .warning{
                border-bottom:1px solid red !important
            }
            .card{
                display: flex;
                margin-bottom: 0.75rem;
                margin-top: 0.5rem;
            }
            .me-2{
                margin-right: 0.5rem;
            }
        </style>
    </head>
    <body className='snippet-body'>
        <form action="payment" method="post" class="flex justify-center items-center min-h-screen bg-blue-300">
            <div class="h-auto w-80 bg-white p-3 rounded-lg">
                <p class="text-xl font-semibold">Trả bằng thẻ</p>
                <div class="card">
                    <img class="me-2" width="45px"
                         src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                         alt="Visa" />
                    <img class="me-2" width="45px"
                         src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                         alt="American Express" />
                    <img class="me-2" width="45px"
                         src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                         alt="Mastercard" />
                    <img class="me-2" width="45px"
                         src="https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg"
                         alt="PayPal acceptance mark" />
                </div>
                <div class="input_text mt-6 relative"> <input type="text" name="cardHolder" class="h-12 pl-7 outline-none px-2 focus:border-blue-900 transition-all w-full border-b " placeholder="Nguyen Van A" /> <span class="absolute left-0 text-sm -top-4">Tên chủ thẻ</span> <i class="absolute left-2 top-4 text-gray-400 fa fa-user"></i> </div>
                <div class="input_text mt-8 relative"> <input type="text" name="cardNumber" class="h-12 pl-7 outline-none px-2 focus:border-blue-900 transition-all w-full border-b " placeholder="0000 0000 0000 0000" data-slots="0" data-accept="\d" size="19" /> <span class="absolute left-0 text-sm -top-4">Số thẻ</span> <i class="absolute left-2 top-[14px] text-gray-400 text-sm fa fa-credit-card"></i> </div>
                <div class="mt-8 flex gap-5 ">
                    <div class="input_text relative w-full"> <input type="text" class="h-12 pl-7 outline-none px-2 focus:border-blue-900 transition-all w-full border-b " placeholder="mm/yyyy" data-slots="my" /> <span class="absolute left-0 text-sm -top-4">Ngày hết hạn</span> <i class="absolute left-2 top-4 text-gray-400 fa fa-calendar-o"></i> </div>
                    <div class="input_text relative w-full"> <input type="text" class="h-12 pl-7 outline-none px-2 focus:border-blue-900 transition-all w-full border-b " placeholder="000" data-slots="0" data-accept="\d" size="3" /> <span class="absolute left-0 text-sm -top-4">CVV</span> <i class="absolute left-2 top-4 text-gray-400 fa fa-lock"></i> </div>
                </div>
                <input type="hidden" name="totalPrice" value="${sessionScope.totalPrice}">
                <p class="text-lg text-center mt-4 text-gray-600 font-semibold">Tổng thanh toán: <fmt:formatNumber value="${sessionScope.totalPrice}" type="currency"/></p>
                <div class="flex justify-center mt-4"> 
                    <button name="choice" value="card" type="submit" class="outline-none pay h-12 bg-orange-600 text-white mb-3 hover:bg-orange-700 rounded-lg w-1/2 cursor-pointer transition-all">Thanh toán</button> 
                </div>
                <hr>
                <p class="text-xl font-semibold mt-3">Hoặc</p>
                <div class="flex justify-center mt-4"> 
                    <button name="choice" value="cashOnDelivery" type="submit" style="min-width: 300px" class="outline-none pay h-12 bg-orange-600 text-white mb-3 hover:bg-orange-700 rounded-lg w-1/2 cursor-pointer transition-all">Thanh toán khi nhận hàng</button> 
                </div>
            </div>
        </form>
        <script type='text/javascript' src='https://cdn.tailwindcss.com/3.0.12'></script>
        <script type='text/javascript' src='https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js'></script>
        <script type='text/javascript' src='#'></script>
        <script type='text/javascript' src='#'></script>
        <script type='text/javascript'>document.addEventListener('DOMContentLoaded', () => {
                for (const el of document.querySelectorAll("[placeholder][data-slots]")) {
                    const pattern = el.getAttribute("placeholder"),
                            slots = new Set(el.dataset.slots || "_"),
                            prev = (j => Array.from(pattern, (c, i) => slots.has(c) ? j = i + 1 : j))(0),
                            first = [...pattern].findIndex(c => slots.has(c)),
                            accept = new RegExp(el.dataset.accept || "\\d", "g"),
                            clean = input => {
                                input = input.match(accept) || [];
                                return Array.from(pattern, c =>
                                    input[0] === c || slots.has(c) ? input.shift() || c : c
                                );
                            },
                            format = () => {
                        const [i, j] = [el.selectionStart, el.selectionEnd].map(i => {
                            i = clean(el.value.slice(0, i)).findIndex(c => slots.has(c));
                            return i < 0 ? prev[prev.length - 1] : back ? prev[i - 1] || first : i;
                        });
                        el.value = clean(el.value).join``;
                        el.setSelectionRange(i, j);
                        back = false;
                    };
                    let back = false;
                    el.addEventListener("keydown", (e) => back = e.key === "Backspace");
                    el.addEventListener("input", format);
                    el.addEventListener("focus", format);
                    el.addEventListener("blur", () => el.value === pattern && (el.value = ""));
                }
            });

            var pay = document.querySelector(".pay");
            var allinputs = document.querySelectorAll(".input_text input")
            pay.addEventListener('click', function (e) {
                e.preventDefault(); // prevent form from submitting
                allinputs.forEach((e) => {
                    e.classList.remove('warning');
                    if (e.value.length < 1) {
                        e.classList.add('warning');
                    }
                });
                // check if any inputs are still empty (i.e., invalid)
                var isAnyInputEmpty = Array.from(allinputs).some(input => input.value.length < 1);
                // if all inputs are valid, submit the form
                if (!isAnyInputEmpty) {
                    var choiceInput = document.createElement('input');
                    choiceInput.type = 'hidden';
                    choiceInput.name = 'choice';
                    choiceInput.value = 'cash';
                    // append the hidden input field to the form
                    e.target.form.appendChild(choiceInput);
                    // submit the form
                    e.target.form.submit();
                }
            });

            allinputs.forEach((all) => {
                all.addEventListener('keyup', function () {
                    if (all.value.length < 1) {
                        all.classList.add('warning');
                    } else {
                        all.classList.remove('warning');
                    }
                });
            });</script>


    </body>
</html>