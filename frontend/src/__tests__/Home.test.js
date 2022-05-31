import {render, screen} from "@testing-library/react";
import Home from "../pages/Home";

describe('Home', function () {

    it('Test renders correctly', async function () {
        render(<Home/>);
        expect(screen.getByRole('heading', {
            name: /foodhaven/i
        })).toHaveTextContent("FoodHaven");
    });

    it('Test 1', async function () {
        render(<Home/>);
        expect(screen.getByRole('login-button'));
        expect(() => screen.getByRole('hello-button')).toThrow();
    });
});