const TEXT_INPUT = "text", NUMBER_INPUT = "number", DATE_INPUT = "date", TIME_INPUT = "time", DATE_TIME_INPUT = "datetime-local";

function include(element) {
    if (element.doInclude !== undefined)
        return element.doInclude ? element.value : ``;
}

function tagParam(parameter) {
    return parameter.input !== undefined ? parameter.output : ``;
}

function inputField(field) {
    return `
        <div class="input_field">
            ${include({
                doInclude: field.label !== undefined,
                value: `<label for="${field.id}">${field.label}</label>`
            })}
            <input type="${field.type}"
                ${tagParam({
                    input: field.id,
                    output: `id="${field.id}"`
                })}
                ${tagParam({
                    input: field.placeholder,
                    output: `placeholder="${field.placeholder}"`
                })}
                ${tagParam({
                    input: field.onInput,
                    output: `oninput="${field.onInput}"`
                })}
                ${include({
                    doInclude: field.isRequired,
                    value: `required`
                })}
                ${tagParam({
                    input: field.max,
                    output: `max="${field.max}"`
                })}
                ${tagParam({
                    input: field.min,
                    output: `min="${field.min}"`
                })}
                ${tagParam({
                    input: field.class,
                    output: `class="${field.class}"`
                })}
                ${tagParam({
                    input: field.value,
                    output: `value="${field.value}"`
                })}
                ${tagParam({
                    input: field.step,
                    output: `step="${field.step}"`
                })}
            >
        </div>
    `;
}

function timepicker(select) {
    function generateOptions() {
        let options = ``;

        for (let i = 0; i <= 23; i++) {
            for (let j = 0; j <= 55; j = j + 5) {
                const time = (i.toString()).padStart(2, '0') + ':' + (j.toString()).padStart(2, '0');
                options += `<option value="${time}">${time}</option>`;
            }
        }

        return options;
    }

    return `
        <div class="time_picker">
            <label for="${select.id}">${select.label}</label>
            <select id="${select.id}" class="time_picker"
                ${include({
                    doInclude: select.isRequired,
                    value: `required`
                })}>
                ${generateOptions()}
            </select>
        </div>
    `;
}



function convertToTwentyFourHourFormat(id) {
    const textLower = document.getElementById(id).value.toLowerCase();
    let [hours, minutes] = textLower.split(':');

    if (textLower.endsWith("am"))
        hours = hours === "12" ? "0" : hours;
    else if (textLower.endsWith('pm'))
        hours = hours === "12" ? hours : String(+hours + 12);

    document.getElementById(id).value = hours.padStart(2,0) + ":" + minutes.slice(0,-2).padStart(2,0);
}