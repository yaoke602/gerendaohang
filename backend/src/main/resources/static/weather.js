/* 天气组件 - 真实数据 + 缓存 + 未来天气 */

// 缓存配置
const CACHE_KEY = 'weather_cache';
const CACHE_DURATION = 30 * 60 * 1000; // 30分钟

// 获取缓存数据
function getCachedWeather() {
    const cached = localStorage.getItem(CACHE_KEY);
    if (!cached) return null;

    const { data, timestamp } = JSON.parse(cached);
    const now = Date.now();

    // 检查是否过期
    if (now - timestamp > CACHE_DURATION) {
        localStorage.removeItem(CACHE_KEY);
        return null;
    }

    return data;
}

// 保存缓存数据
function setCachedWeather(data) {
    const cacheData = {
        data: data,
        timestamp: Date.now()
    };
    localStorage.setItem(CACHE_KEY, JSON.stringify(cacheData));
}

async function updateWeather() {
    const weatherLoc = document.getElementById('weatherLoc');
    const weatherContent = document.getElementById('weatherContent');

    try {
        // 先检查缓存
        const cached = getCachedWeather();
        if (cached) {
            console.log('使用缓存的天气数据');
            displayWeather(cached, weatherLoc, weatherContent);
            return;
        }

        // 步骤1: 通过IP获取城市
        weatherLoc.textContent = '定位中...';

        let city = '武汉';
        weatherLoc.textContent = city;

        // 步骤2: 根据城市获取天气
        const weatherResponse = await fetch(`https://api.cenguigui.cn/api/WeatherInfo/?city=${encodeURIComponent(city)}`);
        const weatherData = await weatherResponse.json();

        if (weatherData.code === 200 && weatherData.data && weatherData.data.today) {
            // 保存到缓存
            setCachedWeather(weatherData.data);
            displayWeather(weatherData.data, weatherLoc, weatherContent);
        } else if (city !== '武汉') {
            // 如果不是武汉且查询失败，尝试使用武汉重试
            console.log('当前城市天气查询失败，使用武汉重试');
            const fallbackResponse = await fetch(`https://api.cenguigui.cn/api/WeatherInfo/?city=${encodeURIComponent('武汉')}`);
            const fallbackData = await fallbackResponse.json();

            if (fallbackData.code === 200 && fallbackData.data && fallbackData.data.today) {
                weatherLoc.textContent = '武汉';
                setCachedWeather(fallbackData.data);
                displayWeather(fallbackData.data, weatherLoc, weatherContent);
            } else {
                throw new Error('天气数据获取失败');
            }
        } else {
            throw new Error('天气数据获取失败');
        }

    } catch (error) {
        console.error('天气更新失败:', error);
        weatherLoc.textContent = '定位失败';
        weatherContent.innerHTML = `
            <div class="weather-temp">--°C</div>
            <div class="weather-desc">暂无数据</div>
        `;
    }
}

// 显示天气数据
function displayWeather(data, weatherLoc, weatherContent) {
    const today = data.today;
    const city = data.city || weatherLoc.textContent;

    weatherLoc.textContent = city;
    weatherContent.innerHTML = `
        <div class="weather-temp">${today.current_temp} / ${today.low}</div>
        <div class="weather-desc">${today.current_cond} | ${data.quality_level || '良'}</div>
    `;

    // 添加未来天气弹窗
    createWeatherPopup(data);
}

// 创建未来天气弹窗
function createWeatherPopup(data) {
    const weatherWidget = document.querySelector('.weather-card');

    // 移除旧的弹窗
    const oldPopup = document.querySelector('.weather-popup');
    if (oldPopup) oldPopup.remove();

    // 创建新弹窗
    const popup = document.createElement('div');
    popup.className = 'weather-popup';

    // 取未来7天天气
    const sevenDay = data.seven_day || [];
    const forecastHTML = sevenDay.slice(0, 7).map(day => {
        const date = new Date(day.date);
        const weekDay = ['日', '一', '二', '三', '四', '五', '六'][date.getDay()];
        const monthDay = `${date.getMonth() + 1}/${date.getDate()}`;

        return `
            <div class="forecast-item">
                <div class="forecast-date">周${weekDay} ${monthDay}</div>
                <div class="forecast-cond">${day.cond}</div>
                <div class="forecast-temp">${day.high} / ${day.low}</div>
            </div>
        `;
    }).join('');

    popup.innerHTML = `
        <div class="popup-header">未来7天天气</div>
        <div class="forecast-list">
            ${forecastHTML}
        </div>
    `;

    weatherWidget.appendChild(popup);

    // 鼠标移入显示，移出隐藏
    weatherWidget.addEventListener('mouseenter', () => {
        popup.style.display = 'block';
    });

    weatherWidget.addEventListener('mouseleave', () => {
        popup.style.display = 'none';
    });
}

// 页面加载时自动调用
updateWeather();
