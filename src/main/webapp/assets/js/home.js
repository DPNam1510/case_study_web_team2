// ================= HEADER THU NHỎ =================
window.addEventListener("scroll", function () {
    const header = document.querySelector("header");
    if (!header) return;
    header.classList.toggle("shrink", window.scrollY > 50);
});

// ================= MENU MOBILE =================
const menuBtn = document.querySelector(".menu-btn");
const navMenu = document.querySelector("nav ul");

if (menuBtn && navMenu) {
    menuBtn.addEventListener("click", () => {
        navMenu.classList.toggle("active");
    });
}

// ================= NÚT BACK TO TOP =================
const backTopBtn = document.createElement("div");
backTopBtn.id = "back-top-btn";
backTopBtn.innerHTML = "⬆";
document.body.appendChild(backTopBtn);

backTopBtn.addEventListener("click", () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
});

window.addEventListener("scroll", () => {
    backTopBtn.classList.toggle("show", window.scrollY > 300);
});

// ================= HIỆU ỨNG FADE-IN =================
const fadeItems = document.querySelectorAll(".fade");

function fadeInOnScroll() {
    fadeItems.forEach(item => {
        const rect = item.getBoundingClientRect();
        if (rect.top < window.innerHeight - 50) {
            item.classList.add("show");
        }
    });
}

window.addEventListener("scroll", fadeInOnScroll);
window.addEventListener("load", fadeInOnScroll);

// ================= FETCH DANH SÁCH DỊCH VỤ TRANG CHỦ =================
async function loadServices() {
    const container = document.querySelector('.service-box');
    if (!container) return; // nếu trang không có service-box thì thôi

    try {
        const res = await fetch('/api/services');
        const services = await res.json();

        container.innerHTML = '';

        services.forEach(s => {
            const div = document.createElement('div');
            div.className = 'service-item';
            div.innerHTML = `<h3>${s.name}</h3><p>${s.description || ''}</p>`;
            container.appendChild(div);
        });
    } catch (err) {
        console.error('Lỗi fetch services:', err);
    }
}

window.addEventListener("load", loadServices);

// ===== LOAD DỊCH VỤ VÀO SELECT TRANG ĐĂNG KÝ =====
async function loadServiceOptions() {
    const select = document.getElementById('service');
    if (!select) return; // nếu trang không có select thì thôi

    try {
        const res = await fetch('/api/services');
        const services = await res.json();

        select.innerHTML = '';
        services.forEach(s => {
            const option = document.createElement('option');
            option.value = s.id;
            option.textContent = s.name;
            select.appendChild(option);
        });
    } catch (err) {
        console.error('Lỗi load services:', err);
    }
}

window.addEventListener("load", loadServiceOptions);

// ===== SUBMIT FORM ĐĂNG KÝ KHÁM =====
const appointmentForm = document.getElementById('appointment-form');

if (appointmentForm) {
    appointmentForm.addEventListener('submit', async function (e) {
        e.preventDefault();

        const serviceSelect = document.getElementById('service');
        const scheduledInput = document.getElementById('scheduled_at');
        const resultEl = document.getElementById('appointment-result');

        const service_id = serviceSelect?.value;
        const scheduled_at = scheduledInput?.value;

        if (!service_id || !scheduled_at) {
            if (resultEl) resultEl.textContent = 'Vui lòng chọn dịch vụ và ngày khám.';
            return;
        }

        try {
            const res = await fetch('/api/appointments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Nếu sau này có token thì thêm ở đây
                    // 'Authorization': 'Bearer ' + token
                },
                body: JSON.stringify({ service_id, scheduled_at })
            });

            let data = {};
            try {
                data = await res.json();
            } catch (_) {
                data = {};
            }

            if (res.ok) {
                if (resultEl) {
                    resultEl.textContent = `Đăng ký thành công! Mã đơn: ${data.code || ''}`;
                }
                appointmentForm.reset();
            } else {
                if (resultEl) {
                    resultEl.textContent = data.message
                        ? `Lỗi: ${data.message}`
                        : 'Đăng ký thất bại, vui lòng thử lại.';
                }
            }
        } catch (err) {
            console.error('Lỗi submit appointment:', err);
            if (resultEl) {
                resultEl.textContent = 'Có lỗi xảy ra, vui lòng thử lại sau.';
            }
        }
    });
}


