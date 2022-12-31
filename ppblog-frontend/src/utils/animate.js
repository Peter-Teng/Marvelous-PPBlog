
const playAnime = (element, animation, infinite = false, prefix = 'animate__') =>
    // We create a Promise and return it
    new Promise((resolve, reject) => {
        const animationName = `${prefix}${animation}`;

        element.classList.add(`${prefix}animated`, animationName);

        // When the animation ends, we clean the classes and resolve the Promise
        function handleAnimationEnd(event) {
            event.stopPropagation();
            element.classList.remove(`${prefix}animated`, animationName);
            resolve('Animation ended');
        }

        if (infinite) {
            element.classList.add("animate__infinite");
            element.addEventListener('mouseleave', handleAnimationEnd, { once: true })
        } else {
            element.addEventListener('animationend', handleAnimationEnd, { once: true });
        }
    });

export default playAnime