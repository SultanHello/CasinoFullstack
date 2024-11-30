// Обработчик кнопки "Играть в казино"
document.getElementById('playCasinoButton').addEventListener('click', async () => {
    const coinAmount = document.getElementById('coinAmount').value;
    if (!coinAmount || isNaN(coinAmount) || coinAmount <= 0) {
        alert('Введите корректное количество монет (число больше 0)!');
        return;
    }

    const token = localStorage.getItem('token'); // Получаем токен из localStorage
    if (!token) {
        alert('Вы не авторизованы! Пожалуйста, войдите в систему.');
        return;
    }

    // Отправляем запрос на сервер для игры в казино
    try {
        const response = await playCasino(token, coinAmount);
        if (response.ok) {
            const data = await response.text(); // Получаем текст (строку) от сервера
            alert(`Игра завершена! Ответ сервера: ${data}`);
        } else {
            const error = await response.text(); // Получаем текст ошибки от сервера
            alert(`Ошибка при игре: ${error}`);
        }
    } catch (err) {
        console.error('Ошибка при отправке запроса:', err);
        alert('Произошла ошибка при подключении к серверу.');
    }
});

// Логика игры
async function playCasino(token, coinAmount) {
    return await fetch('http://localhost:8080/users/playCasino', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Добавляем токен в заголовок
        },
        body: JSON.stringify(coinAmount) // Отправляем количество монет
    });
}

// Логика выхода
document.getElementById('logoutButton').addEventListener('click', () => {
    localStorage.removeItem('token'); // Удаляем токен
    showAuthForm(); // Переходим в форму авторизации
});
