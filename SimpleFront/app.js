document.addEventListener('DOMContentLoaded', () => {
    if (localStorage.getItem('token')) {
        showGameArea(); // Если токен есть, показываем игровую зону
    } else {
        showAuthForm(); // Если нет, показываем форму для входа
    }

    // Переключаем на форму регистрации
    document.getElementById('registerButton')?.addEventListener('click', showRegisterForm);

    // Переключаем на форму входа
    document.getElementById('loginRedirectButton')?.addEventListener('click', showLoginForm);

    // Обработчик формы регистрации
    document.getElementById('registerForm')?.addEventListener('submit', async (event) => {
        event.preventDefault();
        const email = document.getElementById('registerEmail').value.trim();
        const password = document.getElementById('registerPassword').value.trim();
        const age = parseInt(document.getElementById('age').value.trim(), 10);
        const coin = parseInt(document.getElementById('coin').value.trim(), 10);

        if (!email || !password || isNaN(age) || isNaN(coin)) {
            alert('Пожалуйста, заполните все поля корректно.');
            return;
        }

        const response = await registerUser({ email, password, age, coin });
        if (response.success) {
            alert('Регистрация успешна!');
            showLoginForm(); // Переключаем на форму логина
        } else {
            alert(`Ошибка регистрации: ${response.message || 'Попробуйте еще раз.'}`);
        }
    });

    // Обработчик формы логина
    document.getElementById('loginForm')?.addEventListener('submit', async (event) => {
        event.preventDefault();
        const email = document.getElementById('loginEmail').value.trim();
        const password = document.getElementById('loginPassword').value.trim();

        if (!email || !password) {
            alert('Введите корректные данные для входа.');
            return;
        }

        const response = await loginUser({ email, password });
        if (response.ok) {
            const token = await response.text(); // Получаем токен как текст
            localStorage.setItem('token', token); // Сохраняем токен
            showGameArea(); // Показываем игровую зону
        } else {
            alert('Ошибка входа! Проверьте логин или пароль.');
        }
    });
});

// Функция регистрации
async function registerUser(registerData) {
    try {
        const response = await fetch('http://localhost:8080/users/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(registerData)
        });
        return response.ok ? await response.json() : { success: false, message: 'Ошибка регистрации' };
    } catch (error) {
        console.error('Ошибка запроса регистрации:', error);
        return { success: false, message: 'Ошибка сети' };
    }
}

// Функция логина
async function loginUser(loginData) {
    try {
        const response = await fetch('http://localhost:8080/users/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData)
        });
        return response; // Возвращаем весь ответ для обработки
    } catch (error) {
        console.error('Ошибка запроса логина:', error);
        return { ok: false };
    }
}

// Логика показа форм
function showAuthForm() {
    toggleVisibility('auth', true);
    toggleVisibility('gameArea', false);
    toggleVisibility('registerForm', false);
    toggleVisibility('loginForm', true);
}

function showRegisterForm() {
    toggleVisibility('registerForm', true);
    toggleVisibility('loginForm', false);
}

function showLoginForm() {
    toggleVisibility('registerForm', false);
    toggleVisibility('loginForm', true);
}

function showGameArea() {
    toggleVisibility('gameArea', true);
    toggleVisibility('auth', false);
}

// Утилита для управления видимостью
function toggleVisibility(elementId, isVisible) {
    const element = document.getElementById(elementId);
    if (element) {
        element.style.display = isVisible ? 'block' : 'none';
    }
}


