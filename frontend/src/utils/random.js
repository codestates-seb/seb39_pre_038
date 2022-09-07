import { avatars } from './avatars';

export const randomRange = (start, end) => {
  return Math.floor(Math.random() * (end - start) + start);
};

export const randomAvatar = () => {
  const idx = randomRange(0, 123);
  return avatars[idx];
};
